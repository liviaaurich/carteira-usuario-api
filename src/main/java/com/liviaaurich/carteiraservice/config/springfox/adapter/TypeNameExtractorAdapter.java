package com.liviaaurich.carteiraservice.config.springfox.adapter;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.classmate.types.ResolvedArrayType;
import com.fasterxml.classmate.types.ResolvedObjectType;
import com.fasterxml.classmate.types.ResolvedPrimitiveType;
import org.springframework.context.annotation.Primary;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.Collections;
import springfox.documentation.schema.DefaultTypeNameProvider;
import springfox.documentation.schema.ModelNameContext;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.schema.Types;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.EnumTypeDeterminer;
import springfox.documentation.spi.schema.GenericTypeNamingStrategy;
import springfox.documentation.spi.schema.TypeNameProviderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;

import java.lang.reflect.Type;
import java.util.Optional;

@Primary
@Component
public class TypeNameExtractorAdapter extends TypeNameExtractor {
    private final TypeResolver typeResolver;
    private final PluginRegistry<TypeNameProviderPlugin, DocumentationType> typeNameProviders;
    private final EnumTypeDeterminer enumTypeDeterminer;

    public TypeNameExtractorAdapter(TypeResolver typeResolver, PluginRegistry<TypeNameProviderPlugin, DocumentationType> typeNameProviders, EnumTypeDeterminer enumTypeDeterminer) {
        super(typeResolver, typeNameProviders, enumTypeDeterminer);
        this.typeResolver = typeResolver;
        this.typeNameProviders = typeNameProviders;
        this.enumTypeDeterminer = enumTypeDeterminer;
    }

    @Override
    public String typeName(ModelContext context) {
        ResolvedType type = this.resolved(context.getType());
        return Collections.isContainerType(type) ? Collections.containerType(type) : this.getInnerTypeName(type, context);
    }

    private ResolvedType resolved(Type type) {
        return this.typeResolver.resolve(type);
    }

    private String getGenericTypeName(ResolvedType resolvedType, ModelContext context) {
        Class<?> erasedType = resolvedType.getErasedType();
        GenericTypeNamingStrategy namingStrategy = context.getGenericNamingStrategy();
        ModelNameContext nameContext = new ModelNameContext(resolvedType.getErasedType(), context.getDocumentationType());
        String simpleName = Optional.ofNullable(Types.typeNameFor(erasedType)).orElse(this.getTypeName(nameContext));
        StringBuilder sb = new StringBuilder(String.format("%s%s", simpleName, namingStrategy.getOpenGeneric()));
        boolean first = true;

        for(int index = 0; index < erasedType.getTypeParameters().length; ++index) {
            ResolvedType typeParam = resolvedType.getTypeParameters().get(index);
            if (first) {
                sb.append(this.getInnerTypeName(typeParam, context));
                first = false;
            } else {
                sb.append(String.format("%s%s", namingStrategy.getTypeListDelimiter(), this.getInnerTypeName(typeParam, context)));
            }
        }

        sb.append(namingStrategy.getCloseGeneric());
        return sb.toString();
    }

    private String getInnerTypeName(ResolvedType type, ModelContext context) {
        return !type.getTypeParameters().isEmpty() && type.getErasedType().getTypeParameters().length > 0 ? this.getGenericTypeName(type, context) : this.getSimpleTypeName(type, context);
    }

    private String getSimpleTypeName(ResolvedType type, ModelContext context) {
        Class<?> erasedType = type.getErasedType();
        if (type instanceof ResolvedPrimitiveType) {
            return Types.typeNameFor(erasedType);
        } else if (this.enumTypeDeterminer.isEnum(erasedType)) {
            return "string";
        } else if (type instanceof ResolvedArrayType) {
            GenericTypeNamingStrategy namingStrategy = context.getGenericNamingStrategy();
            return String.format("Array%s%s%s", namingStrategy.getOpenGeneric(), this.getSimpleTypeName(type.getArrayElementType(), context), namingStrategy.getCloseGeneric());
        } else {
            if (type instanceof ResolvedObjectType) {
                String typeName = Types.typeNameFor(erasedType);
                if (typeName != null) {
                    return typeName;
                }
            }

            return this.getTypeName(new ModelNameContext(type.getErasedType(), context.getDocumentationType()));
        }
    }

    private String getTypeName(ModelNameContext context) {
        TypeNameProviderPlugin selected = this.typeNameProviders.getPluginOrDefaultFor(context.getDocumentationType(), new DefaultTypeNameProvider());
        return selected.nameFor(context.getType());
    }
}

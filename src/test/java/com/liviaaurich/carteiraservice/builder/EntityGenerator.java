package com.liviaaurich.carteiraservice.builder;

public abstract class EntityGenerator<T, E> {

    public abstract E createDto();

    public abstract T createEntity();
}

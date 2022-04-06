package com.example.jsontodb.mapper;

public interface GenericMapper <D, E> {
    D toDto(E e);
}

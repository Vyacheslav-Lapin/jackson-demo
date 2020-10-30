package ru.vlapin.demo.jacksondemo.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.demo.jacksondemo.model.Cat;

public interface CatRepository extends JpaRepository<Cat, UUID> {
}

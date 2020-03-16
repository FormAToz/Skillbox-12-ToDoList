package main.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemsRepository extends CrudRepository<ToDoItem, Long> {

    @Query(value = "from ToDoItem item where item.title like concat('%', :title, '%')")
    List<ToDoItem> searchByTitle(@Param("title")String text);
}

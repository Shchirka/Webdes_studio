package com.ipz_project.Webdes.repo;

import com.ipz_project.Webdes.models.Price_list;
import org.springframework.data.repository.CrudRepository;

/**Интерфейс связь модели с базой данных
 * @author Tonya Shchirska */
public interface Price_listRepository extends CrudRepository<Price_list, Long> {
}

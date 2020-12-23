package com.ipz_project.Webdes.repo;

import com.ipz_project.Webdes.models.Works;
import org.springframework.data.repository.CrudRepository;

/**Интерфейс связь модели с базой данных
 * @author Tonya Shchirska */
public interface WorksRepository extends CrudRepository<Works, Long> {
}

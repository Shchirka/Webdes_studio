package com.ipz_project.Webdes.repo;

import com.ipz_project.Webdes.models.Orders;
import org.springframework.data.repository.CrudRepository;

/**Интерфейс связь модели с базой данных
 * @author Tonya Shchirska */
public interface OrdersRepository extends CrudRepository<Orders, Long> {
}

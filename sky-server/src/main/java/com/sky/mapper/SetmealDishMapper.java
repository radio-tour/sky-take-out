package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 批量插入 setmeal dish
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据菜品 id 查询对应的套餐 id
     * @param dishIds
     * @return
     */
    // select setmeal_id from setmeal_dish where dish_id in(dishIds)
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}

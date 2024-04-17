package DAO;

import java.util.ArrayList;

public interface DAO_Interface<T, ID>{
    int insert(T entity); // Thêm hàng mới vào bảng

    int update(T entity); //Sửa hàng đã có bảng theo khóa chính

    int delete(T entity); // Xóa cột đã có  vào bảng theo khóa chính

    ArrayList<T> findAll(); // Lấy tất cả các hàng trong bảng

    T findById(ID id); // Tìm hàng trong bảng theo khóa chính

    ArrayList<T> findByCondition(String condition); // Tìm hàng trong bảng theo điều kiện

}

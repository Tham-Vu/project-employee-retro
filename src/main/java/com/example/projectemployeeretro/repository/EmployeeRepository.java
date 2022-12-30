package com.example.projectemployeeretro.repository;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.query.CountEmployeeByRoleName;
import com.example.projectemployeeretro.query.CountEmployeeRole;
import com.example.projectemployeeretro.query.ICountEmployeeRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   //JPQL
//   @Query("from Employee e where e.username = :name")
   //Native SQL
   @Query(value = "select * from employee e where e.role_id = 2", nativeQuery = true)
   List<Employee> getEmployee(@Param("name") String username);
   //JPQL
   @Query(value = "select e from Employee e order by e.id")
   Page<Employee> findAllEmployeeWithPagination(Pageable pageable);
   @Query(value = "select e from Employee e")
   Slice<Employee> findAllEmployeeWithSlice(Pageable pageable);
   Employee findEmployeeByUsername(String userName);
   @Query(value = "select e.email from Employee e")
   List<String> getAllEmail();
   @Query("SELECT new com.example.projectemployeeretro.query.CountEmployeeRole(r.id, COUNT(e.role)) "
           + "FROM Employee e, Role r Where e.role.id = r.id GROUP BY r.id ORDER BY r.id DESC")
   List<CountEmployeeRole> countTotalEmployeeByRole();
   @Query("select r.id as typeRole, count(e.role) as total from Employee e, Role r where e.role.id = r.id group by r.id")
   List<ICountEmployeeRole> countTotalEmployeeByRoleInterface();
   @Query(value = "select new com.example.projectemployeeretro.query.CountEmployeeByRoleName(r.name, e.id) from Role r left outer join Employee e on r.id = e.role.id")
   List<CountEmployeeByRoleName> getEmployeeByRoleName();
}

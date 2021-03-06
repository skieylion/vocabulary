package jentus.vocabulary.repository;

import jentus.vocabulary.model.Context;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContextRepository extends CrudRepository<Context,Long>, ContextRepositoryCustom {
    List<Context> findAll();

    @Modifying
    @Query(value = "delete SetAndContext where setId=:setId and contextId=:contextId",nativeQuery = true)
    void detachContextFromSet(@Param("contextId") long contextId, @Param("setId") long setId);

    @Query("select ctx from Context ctx where id in :listId")
    List<Context> findAllByListId(@Param("listId") List<Long> listId);
}

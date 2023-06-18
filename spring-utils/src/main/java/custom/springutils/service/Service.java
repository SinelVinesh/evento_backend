package custom.springutils.service;

import java.util.List;

import custom.springutils.util.ListResponse;

public interface Service <E> {

    byte[] createPDF () throws Exception;

    ListResponse search (Object filter, Integer page) throws Exception;

    int getPageSize() throws Exception;

    List<E> findAll(int page) throws Exception;

    E create(E obj) throws Exception;

    E update(E obj) throws Exception;

    void delete(Long id) throws Exception;

    E findById(Long id) throws Exception;

    Iterable<E> findAll() throws Exception;

    List<E> saveAll(List<E> obj) throws Exception;

}

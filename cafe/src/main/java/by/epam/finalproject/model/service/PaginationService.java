package by.epam.finalproject.model.service;

public class PaginationService {

    public static int offset(int pageSize, int currentPage){
        return pageSize * (currentPage - 1);
    }

    public static int lastPage(int pages, int pageSize, int totalRecords){
        return pages * pageSize < totalRecords ? pages + 1 : pages;
    }

    public static int pages(int totalRecords, int pageSize){
        return totalRecords / pageSize;
    }
}

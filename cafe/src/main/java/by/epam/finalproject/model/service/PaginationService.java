package by.epam.finalproject.model.service;

/**
 * The type Pagination service.
 */
public class PaginationService {

    /**
     * Offset int.
     *
     * @param pageSize    the page size
     * @param currentPage the current page
     * @return the int
     */
    public static int offset(int pageSize, int currentPage){
        return pageSize * (currentPage - 1);
    }

    /**
     * Last page int.
     *
     * @param pages        the pages
     * @param pageSize     the page size
     * @param totalRecords the total records
     * @return the int
     */
    public static int lastPage(int pages, int pageSize, int totalRecords){
        return pages * pageSize < totalRecords ? pages + 1 : pages;
    }

    /**
     * Pages int.
     *
     * @param totalRecords the total records
     * @param pageSize     the page size
     * @return the int
     */
    public static int pages(int totalRecords, int pageSize){
        return totalRecords / pageSize;
    }
}

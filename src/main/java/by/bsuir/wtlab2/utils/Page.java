package by.bsuir.wtlab2.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private List<T> content;

    public int getTotalPages() {
        return (int) Math.ceil((double) totalElements / pageSize);
    }

    public boolean hasNextPage() {
        return pageNumber < getTotalPages();
    }

    public boolean hasPreviousPage() {
        return pageNumber > 1;
    }
}

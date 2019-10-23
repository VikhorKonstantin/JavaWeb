package by.training.paragliding.controller;

import by.training.paragliding.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public final class PaginationHelper {

    private PaginationHelper() {
    }

    public static int calcOffsetAndSetPageIndex(
            final HttpServletRequest request, final int size,
            final int rowsOnPage)
            throws ControllerException {
        try {
            var pageIndex = request.getParameter("pageIndex");
            int page = Optional.ofNullable(pageIndex)
                    .map(Integer::parseInt)
                    .orElse(1);
            int lastPage = calculateLastPageIndex(size, rowsOnPage);
            if (page <= lastPage && page > 0) {
                request.setAttribute("lastPageIndex", lastPage);
                request.setAttribute("pageIndex", page);
                return (page - 1) * rowsOnPage;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            throw new ControllerException(e);
        }
    }

    private static int calculateLastPageIndex(final int size,
                                              final int rowsOnPage) {
        var lastPageIndex = size / rowsOnPage;
        return (size % rowsOnPage == 0) ? lastPageIndex : lastPageIndex + 1;
    }

}
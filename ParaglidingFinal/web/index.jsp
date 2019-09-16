<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="request" method="post">
    <input type="hidden" name="actionName" value="READ">
    <input type="text" name="civlId">
    <button type="submit">
      read
    </button>
      ${sportsman}
  </form>
  $END$
  </body>
</html>

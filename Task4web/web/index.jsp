<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<html>

<head>

     <head>
          <style>
               table,
               th,
               td {
                    border: 1px solid black;
               }
          </style>
     </head>
</head>

<body>

     <form action="parse" method="POST">
          <input type="hidden" name="actionName" value="PARSE">
          <input type="radio" name="radios" value="dom">
          DOM-parser<br>
          <input type="radio" name="radios" value="sax">
          SAX-parser<br>
          <input type="radio" name="radios" value="stax">
          StAX-parser<br>
          <input type="textarea" name="xmlFileName">
          xmlFileName<br>
          <input type="textarea" name="xsdFileName">
          xsdFileName<br>
          <input type="submit" value="Submit">
          <button type="button" name="back" onclick="history.back()">Back</button>
     </form>
     <table>
          <tr>
               <td>GEM_ID</td>
               <td>AUCTIN_DATE</td>
               <td>PRECIOUSNESS</td>
               <td>GEM_TYPE</td>
               <td>ORIGIN</td>
               <td>COLOR</td>
               <td>TRANSPARENCY</td>
               <td>CUTTING_METHOD</td>
               <td>VALUE</td>
               <td>FOUNDATION_DATE</td>
               <td>NAME</td>
               <td>FOUNDATION_DATE</td>
          </tr>

          <c:forEach items="${gems}" var="gem">
               <tr>
                    <td>${gem.gemId}</td>
                    <td>${gem.auctionDate}</td>
                    <td>${gem.preciousness}</td>
                    <td>${gem.gemType}</td>
                    <td>${gem.origin}</td>
                    <td>${gem.visualParameters.color}</td>
                    <td>${gem.visualParameters.transparency}</td>
                    <td>${gem.visualParameters.cuttingMethod}</td>
                    <td>${gem.value}</td>
                    <td>${gem.preciousness}</td>
                    <c:if test="${gem['class'].simpleName eq 'FamousGem'}">
                         <td>${gem.name}</td>
                    </c:if>
                    <c:if test="${gem['class'].simpleName eq 'FamousGem'}">
                         <td>${gem.foundationDate}</td>
                    </c:if>

               </tr>
          </c:forEach>
     </table>
</body>

</html>
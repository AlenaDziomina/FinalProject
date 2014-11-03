<%-- 
    Document   : searching
    Created on : 28.10.2014, 1:04:33
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="ctg" uri="/WEB-INF/tlds/custom.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale scope="session" value="${sessionScope.locale}" />
<fmt:setBundle basename="resource.pagecontent" var="rb" />

<div class="parameterRowB" >
    <div id="show">
        <div class="mid input">
            <h1 class="cntr labelH">TourSearching...</h1>
        </div>
        <div class="input">
            <input class="yellow awesome" type="submit" value="<fmt:message key="showParams" bundle="${ rb }" />" onclick="hideSearching(false)"/>
        </div>
    </div>

    <div id="hide">
        <div class="mid input">
            <h1 class="cntr labelH">TourSearching:</h1>
        </div>
        <div class="input">
            <input class="yellow awesome" type="submit" value="<fmt:message key="hideParams" bundle="${ rb }" />" onclick="hideSearching(true)"/>
        </div>
    </div>

    <div id="searching">
        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="tourDepartDate" bundle="${ rb }" />:</label>
            </div>
            <div class="input">
                <input type="checkbox" id="allDepartDate" name="departDateBox" class="boxMar" value="0" onchange="disable('departDate', 'allDepartDate')"/><fmt:message key="anyDepartDate" bundle="${ rb }" /><br>
                <div id="departDate">

                    <jsp:include page="/WEB-INF/other/dinamcalend.jsp" />
                    <label class="small padR labelH"><fmt:message key="from" bundle="${ rb }" /></label>
                    <input readonly="true" type="text" id="dateFrom" name="departDateFrom" class="inputLineContainer" />
                    <label class="small padR labelH"><fmt:message key="to" bundle="${ rb }" />:</label>
                    <input readonly="true" type="text" id="dateTo" name="departDateTo" class="inputLineContainer" />
                </div>
            </div>
        </div>

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="tourDaysCount" bundle="${ rb }" />:</label>
            </div>
            <div class="input">
                <input type="checkbox" id="allDaysCount" name="daysCountBox" class="boxMar" value="0" onchange="disable('daysCount', 'allDaysCount')"/><fmt:message key="anyDaysCount" bundle="${ rb }" /><br>
                <div id="daysCount">
                    <label class="small padR labelH"><fmt:message key="from" bundle="${ rb }" /></label>
                    <select id="currDaysCountFrom" name="daysCountFromSelection" class="selectContainer" size="1" >      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach var="i" begin="1" end="45">
                            <option class="selectItem" value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    <script>select("currDaysCountFrom", ${currDaysCountFrom});</script>
                    <label class="small padR labelH"><fmt:message key="to" bundle="${ rb }" />:</label>
                    <select id="currDaysCountTo" name="daysCountToSelection" class="selectContainer" size="1" >      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach var="i" begin="1" end="45">
                            <option class="selectItem" value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    <script>select("currDaysCountTo", ${currDaysCountTo});</script>
                </div>
            </div>
        </div>

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="tourPrice" bundle="${ rb }" />:</label>
            </div>
            <div class="input">
                <input type="checkbox" id="allPrice" name="priceBox" class="boxMar" value="0" onchange="disable('prices', 'allPrice')"/><fmt:message key="anyPrice" bundle="${ rb }" /><br>
                <div id="prices">
                    <label class="small padR labelH"><fmt:message key="from" bundle="${ rb }" /></label>
                    <select id="currPriceFrom" name="priceFromSelection" class="selectContainer" size="1" >      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach var="i" begin="1" end="20">
                            <option class="selectItem" value="${i}">${i*priceStep}</option>
                        </c:forEach>
                    </select>
                    <script>select("currPriceFrom", ${currPriceFrom});</script>
                    <label class="small padR labelH"><fmt:message key="to" bundle="${ rb }" />:</label>
                    <select id="currPriceTo" name="priceToSelection" class="selectContainer" size="1" >      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach var="i" begin="1" end="20">
                            <option class="selectItem" value="${i}">${i*priceStep}</option>
                        </c:forEach>
                    </select>
                    <script>select("currPriceTo", ${currPriceTo});</script>
                    <label class="small padR labelH"><fmt:message key="$" bundle="${ rb }" /></label>
                </div>                 
            </div>
        </div>

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="tourDiscount" bundle="${ rb }" />:</label>
            </div>
            <div class="input">
                <label class="small padR labelH"><fmt:message key="from" bundle="${ rb }" /></label>
                <select id="currDiscountFrom" name="discountFromSelection" class="selectContainer" size="1" >      
                    <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                    <c:forEach var="i" begin="1" end="10">
                        <option class="selectItem" value="${i}">${i*discountStep}</option>
                    </c:forEach>
                </select>
                <label class="small padR labelH">%</label>
                <script>select("currDiscountFrom", ${currDiscountFrom});</script>
            </div>
        </div>     

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="selectHotelStars" bundle="${ rb }" />:</label>    
            </div>
            <div class="input">
                <label class="small padR labelH"><fmt:message key="from" bundle="${ rb }" /></label>
                <select id="currStars" name="starsSelection" class="selectContainer" size="1" >      
                    <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                    <c:forEach var="i" begin="1" end="5">
                        <option class="selectItem" value="${i}">${i}*</option>
                    </c:forEach>
                </select>
                <script>select("currStars", ${currStars});</script>    
            </div>    
        </div>                

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="selectTourType" bundle="${ rb }" />:</label>
            </div>
            <div class="input">
                <select id="tourType" class="selectContainer" size="1">      
                        <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                        <c:forEach items="${tourTypeList}" var="row">
                            <option class="selectItem" value="${row.idTourType}"><fmt:message key="${row.nameTourType}" bundle="${ rb }" /></option>   
                        </c:forEach>
                </select>
                <script>select("tourType", ${currTourType});</script>
            </div>    
        </div>

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="selectTransMode" bundle="${ rb }" />:</label>    
            </div>
            <div class="input">
                <select id="transMode" class="selectContainer" size="1">      
                    <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                    <c:forEach items="${transModeList}" var="row">
                        <option class="selectItem" value="${row.idMode}"><fmt:message key="${row.nameMode}" bundle="${ rb }" /></option>
                    </c:forEach>
                </select>
                <script>select("transMode", ${currTransMode});</script>
            </div>    
        </div>

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="selectCountries" bundle="${ rb }" />: </label>  
            </div>
            <div class="input">
                <input type="checkbox" id="allCountries" name="countryTag" class="boxMar" value="0" onchange="disable('countries', 'allCountries')"/><fmt:message key="anyCountry" bundle="${ rb }" /><br>
                <div id="countries" class="checkBoxGr">
                    <c:forEach items="${countryTagList}" var="row">
                        <input type="checkbox" name="countryTag" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                    </c:forEach>
                </div>  
            </div>
        </div>

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="selectCities" bundle="${ rb }" />: </label>  
            </div>
            <div class="input">
                <input type="checkbox" id="allCities" name="cityTag" class="boxMar" value="0" onchange="disable('cities', 'allCities')"/><fmt:message key="anyCity" bundle="${ rb }" /><br>
                <div id="cities" class="checkBoxGr">
                    <c:forEach items="${cityTagList}" var="row">
                        <input type="checkbox" name="cityTag" value="${row.idCity}"/><fmt:message key="${row.name}" bundle="${ rb }" /><br>
                    </c:forEach>
                </div>  
            </div>
        </div>

        <div class="parameterRow">
            <div class="mid input">
                <label class="padL labelH"><fmt:message key="selectHotels" bundle="${ rb }" />: </label>  
            </div>
            <div class="input">
                <input type="checkbox" id="allHotels" name="hotelTag" class="boxMar" value="0" onchange="disable('hotels', 'allHotels')"/><fmt:message key="anyHotel" bundle="${ rb }" /><br>
                <div id="hotels" class="parameterRow">
                    <div class="padinput">
                        <div id="currHotelTag" class="checkBoxGr"></div>
                    </div>
                    <div class="large padinput">
                        <div class="parameterRow">
                            <div class="mid input">
                                <label class="small padL labelH">1. <fmt:message key="selectCountry" bundle="${ rb }" />:</label>
                            </div>
                            <div class="input">
                                <select id="currCountry" class="selectContainer" size="1" onchange="postTour('controller', 'ifCountrySelected', 'POST')">      
                                    <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                    <c:forEach items="${countryList}" var="row">
                                        <option class="selectItem" value="${row.idCountry}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                    </c:forEach>
                                </select>
                                <script>select("currCountry", ${currIdCountry});</script>
                            </div>
                        </div>
                        <div class="parameterRow">
                            <div class="mid input">
                                <label class="small padL labelH">2. <fmt:message key="selectCity" bundle="${ rb }" />:</label>
                            </div>
                            <div class="input">
                                <select id="currCity" class="selectContainer" size="1" onchange="postTour('controller', 'ifCitySelected', 'POST')">      
                                    <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                    <c:forEach items="${cityList}" var="row">
                                        <option class="selectItem" value="${row.idCity}"><fmt:message key="${row.name}" bundle="${ rb }" /></option>
                                    </c:forEach>
                                </select> 
                                <script>select("currCity", ${currIdCity});</script>
                            </div>
                        </div>
                        <div class="parameterRow">
                            <div class="mid input">
                                <label class="small padL labelH">3. <fmt:message key="selectHotel" bundle="${ rb }" />:</label>
                            </div>
                            <div class="input">
                                <select id="currHotel" class="selectContainer" size="1">      
                                    <option class="selectItem" value="0"> - <fmt:message key="select" bundle="${ rb }" /> - </option>
                                    <c:forEach items="${hotelList}" var="row">
                                        <option class="selectItem" value="${row.idHotel}">${row.name}</option>
                                    </c:forEach>
                                </select> 
                                <script>select("currHotel", ${currIdHotel});</script>    
                            </div>
                        </div>
                        <div class="parameterRow">
                            <div class="mid input">
                                <label class="small padL labelH">4. <fmt:message key="selectHotel" bundle="${ rb }" />:</label>
                            </div>
                            <div class="input">
                                <a class="small blue awesome" onclick="funcAdd()">ADD HOTEL</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form id="srcTour" name="searchTour" method="POST" action="controller">
            <div class="parameterRow centrale">
                <input type="hidden" name="command" value="searchTour" />
                <input class="large green awesome" type="submit" value="<fmt:message key="search" bundle="${ rb }" />" onclick="saveAllSearch()" />
                <div id="erNote">${errorSaveData}</div>
                <div id="erNote">${errorReason}</div>
                <div id="erAdminNote">${errorAdminMsg}</div>
                
            </div>
        </form>                        


    </div>

    <script>
        hideSearching(${isHidden});
        checkBox('allDepartDate', ${allDepartDate}, 'departDate');
        checkBox('allDaysCount', ${allDaysCount}, 'daysCount');
        checkBox('allPrice', ${allPrice}, 'prices');
        checkBox('allCountries', ${allCountries}, 'countries');
        checkBox('allCities', ${allCities}, 'cities');
        checkBox('allHotels', ${allHotels}, 'hotels');
    </script>

    <c:forEach items="${currCountryTag}" var="tag">
        <script>
            check("countryTag", ${tag});
        </script>
    </c:forEach>

    <c:forEach items="${currCityTag}" var="tag">
        <script>
            check("cityTag", ${tag});
        </script>
    </c:forEach>

    <c:forEach items="${hotelTagList}" var="row">
        <script>
            restoreCheck("${row.idHotel}", "${row.name}");
        </script>
    </c:forEach>

</div>
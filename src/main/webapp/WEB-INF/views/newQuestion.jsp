<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:basepage title="New Question">
    <jsp:body>
        <h1>New Question</h1>
        <form action="newQuestion" method="post">
            <label for="question">Question</label>
            <input type="text" name="question" id="question" />
            <br />
            <label for="answer">Answer</label>
            <input type="text" name="answer" id="answer" />
            <br />
            <input type="submit" value="Submit" />
        </form>
    </jsp:body>
</t:basepage>

<nav class="navbar">
    <div class="navbar--container">
        <div class="navbar--heading">
            <a href="/"><i class="material-icons md-light md-36">home</i></a>
        </div>
        <div class="search-container">
            <form class="search-form" action="/tournaments" method="GET">
                <input class="search-input" type="text" id="search" name="search" placeholder="Search..." />
                <button class="search-button" type="submit"><i class="search-icon material-icons">search</i></button>
            </form>
        </div>
        <div class="navbar--nav">
            <c:choose>
                <c:when test="${!isAuthenticated}">
                    <a href="/login" class="navbar--item"><i class="material-icons md-light">lock</i>Login</a>
                </c:when>
                <c:otherwise>
                    <a href="/profile" class="navbar--item"><i class="material-icons md-light">account_circle</i>${username}</a>
                    <a href="/logout" class="navbar--item">Logout</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
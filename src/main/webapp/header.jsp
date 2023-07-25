<header>
 <nav class="navbar navbar-expand-md navbar-dark"
  style="background-color: tomato">
  <div>
   <h2 class="navbar-nav">Todos</h2>
  </div>

  <ul class="navbar-nav navbar-collapse justify-content-end">
   <li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
   <li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
  </ul>
 </nav>
</header>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<!-- Include Bootstrap CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
/* Custom CSS to center and shorten text fields */
.custom-form {
    max-width: 400px;
    margin: 0 auto;
    margin-top: 100px;
}
.custom-form .form-group input[type="text"], .custom-form .form-group input[type="password"]{
    text-align: center;
    max-width: 100px;
    margin: 0 auto;
}
</style>
</head>
<body>
<div class="container mt-5">
<h1 class="text-center">Equity Apartments</h1>
<form action="login" method="post" class="mt-4">
<div class="form-group">
<input type="text" name="username" class="form-control"
                    placeholder="Username" required>
</div>
<div class="form-group">
<input type="password" name="password" class="form-control"
                    placeholder="Password" required>
</div>
<button type="submit" class="btn btn-primary btn-block">Login</button>
</form>

</div>
</body>
</html>
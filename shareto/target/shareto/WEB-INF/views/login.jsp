<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="./base.jsp" %>
</head>
<body style="background-color: aliceblue;">
    <div class="container mt-5">
        <h2 class="text-center mb-4">Login to Shareto</h2>
        <div class="row">
            <div class="col"></div>
            <div class="col">
                <div class="card shadow-lg p-3 mb-5 rounded" style="background-color: aliceblue;">
                    <div class="card-body">
                        <form action="login" method="post">
                            <div class="mb-3">
                                <label for="exampleFormControlInput2" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="exampleFormControlInput2" placeholder="name@example.com" name="email">
                            </div>
                            <div class="mb-3">
                                <label for="inputPassword" class="form-label">Password</label>
                                <input type="password" class="form-control" id="inputPassword" name="password">
                            </div>
                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-success mb-3">Login</button>
                                <p>Don't have an account? <a href="register">Register</a></p>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <div class="col"></div>
        </div>
    </div>
</body>
</html>
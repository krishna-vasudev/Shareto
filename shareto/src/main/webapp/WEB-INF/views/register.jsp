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
        <h2 class="text-center mb-4">Register to Shareto</h2>
        <div class="row">
            <div class="col"></div>
            <div class="col">
                <div class="card shadow-lg p-3 mb-5 rounded" style="background-color: aliceblue;">
                    <div class="card-body">
                        <form action="register" method="post">
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Name</label>
                                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Enter Your name" name="name">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput2" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="exampleFormControlInput2" placeholder="name@example.com" name="email">
                            </div>
                            <div class="mb-3">
                                <label for="inputPassword" class="form-label">Password</label>
                                <input type="password" class="form-control" id="inputPassword" name="password">
                            </div>
                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-danger mb-3">Register</button>
                                <p>Already have an account? <a href="login">Login</a></p>
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
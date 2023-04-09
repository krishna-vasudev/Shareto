<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="./base.jsp" %>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">Shareto</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            </ul>
            <form class="d-flex">
              <a href="login" class="btn btn-outline-success mx-2" type="submit">Login</a>
              <a href="register" class="btn btn-outline-danger" type="submit">Register</a>
            </form>
          </div>
        </div>
      </nav>
     
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
            <img src="https://i0.wp.com/davidsusman.com/wp-content/uploads/2019/08/canstockphoto14477663-e1564684097149.jpg?fit=400%2C300" class="d-block w-100" alt="..." style="height: 600px; object-fit: cover;">
            <div class="carousel-caption d-none d-md-block">
              <button type="button" class="btn btn-success">Shareto</button>
              <button type="button" class="btn btn-danger">Outlook</button>
              <button type="button" class="btn btn-warning">Connect</button>
                <p style="font-weight: bold;">Share your opinions.</p>
            </div>
            </div>
            <div class="carousel-item">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCayDgWSY44dLavCEyTJw4z3f3pXiEc2USyUVQ30o4hg&usqp=CAU&ec=48665701" class="d-block w-100" alt="..." style="height: 600px; object-fit: cover;">
            <div class="carousel-caption d-none d-md-block">
              <button type="button" class="btn btn-success">Shareto</button>
              <button type="button" class="btn btn-danger">Outlook</button>
              <button type="button" class="btn btn-warning">Connect</button>
                <p style="color: red; font-weight: bold;">Write a outlook to express your views.</p>
            </div>
            </div>
            <div class="carousel-item">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8dlIev0mjNMfWGdv3n8mZPYIHLu3gDO0YcuT0diUbynroLGw5qIzcVltocki-AKKVEhkeZ8Mkru0&usqp=CAU&ec=48665701" class="d-block w-100" alt="..." style="height: 600px; object-fit: cover;">
            <div class="carousel-caption d-none d-md-block">
              <button type="button" class="btn btn-success">Shareto</button>
              <button type="button" class="btn btn-danger">Outlook</button>
              <button type="button" class="btn btn-warning">Connect</button>
                <p style="color: antiquewhite; font-weight: bold;">Connect with people from around the globe.</p>
            </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
        </div>
    
    
</body>
</html>
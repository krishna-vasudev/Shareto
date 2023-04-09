<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="./base.jsp" %>
</head>
<body style="background-color: aliceblue;">

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
              <button  class="btn btn-outline-warning mx-2" data-bs-toggle="modal" data-bs-target="#OutlookModal" onclick="return false;">Outlook</button>
              <a href="logout" class="btn btn-outline-danger" type="submit">Logout</a>
            </form>
          </div>
        </div>
      </nav>

      <div class="modal fade" id="OutlookModal" tabindex="-1" aria-labelledby="LoginModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <div class="container text-center">
                 <h5 class="modal-title" id="LoginModalLabel">Share your Outlook</h5>
              </div>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="outlook" method="post">
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" aria-describedby="emailHelp" name="title">
                    </div>
                    <div class="mb-3">
                        <label for="body" class="form-label">Content</label>
                        <textarea class="form-control" id="body" rows="3" name="body"></textarea>
                    </div>
                    <div class="container text-center">
                        <button type="submit" class="btn btn-warning">Outlook</button>
                    </div>
                  </form>
            </div>
            <div class="modal-footer">
            </div>
          </div>
        </div>
      </div>
    <div class="container mt-3">
      <div class="row">
        <div class="col"></div>
        <div class="col-5">

          <c:forEach var="outlook" items="${outlooks}">
            <div class="card border-secondary shadow-lg mb-3 rounded" >
              <div class="card-header bg-transparent border-success">
                <c:if test="${outlook.user.id==loggedInUser.id}">
                  <span style="margin-left: 430px; margin-right: 10px;"><button data-bs-toggle="modal" data-bs-target="#OutlookModalUpdate${outlook.id}" style="padding: 0; background: none; border: none;"><i class="fa-solid fa-pen-nib" style="color: blue; font-size: 20px;"></i></button></span>
                  <span><button data-bs-toggle="modal" data-bs-target="#OutlookModalDelete${outlook.id}" style="padding: 0; background: none; border: none;"><i class="fa-solid fa-trash-can" style="color: red; font-size: 20px;"></i></button></span>
                  <br>
                </c:if>
                <span><a href="" style="text-decoration: none;"><i class="fa-solid fa-user" style="font-size: 30px; color: black;"></i>&nbsp;&nbsp;</a><b>${outlook.user.name}</b></span>
                <br>
                <span style="color: gray;">${outlook.updated_at_formatted}</span>
              </div>
              <div class="card-body text-dark text-center">
                <h5 class="card-title">${outlook.title}</h5>
                <p class="card-text">${outlook.body}</p>
              </div>
              <div class="card-footer bg-transparent border-success">
                <br>
                <span id="loveajaxresponse${outlook.id}">
                  <c:choose>
                    <c:when test="${outlook.lovedByLoggedInUser==false}">
                      <span><a id="loveajax${outlook.id}" href="#" style="text-decoration: none;"><i class="fa-regular fa-heart" style="font-size: 20px;color: #000;"></i></a>&nbsp;&nbsp;${outlook.loves.size()} Loves</span>
                    </c:when>
                    <c:otherwise>
                      <span><a id="loveajax${outlook.id}" href="#" style="text-decoration: none;"><i class="fa-solid fa-heart" style="font-size: 20px;color: red;"></i></a>&nbsp;&nbsp;${outlook.loves.size()} Loves</span>
                    </c:otherwise>
                  </c:choose>
                </span>
                
                <span id="commentsCount${outlook.id}" style="margin-left: 200px;"><button data-bs-toggle="modal" data-bs-target="#OutlookCommentModal${outlook.id}" style="padding: 0; background: none; border: none;"><i class="fa-regular fa-comment" style="font-size: 20px;color: #000;"></i></button>&nbsp;&nbsp;${outlook.comments.size()} Comments</span>
              </div>
            </div>
            

            <!-- delete modal -->
            <div class="modal fade" id="OutlookModalDelete${outlook.id}" tabindex="-1" aria-labelledby="LoginModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body text-center">
                      Are you sure you want to delete the outlook? All related comments and loves will also be deleted
                      permanently!
                  </div>
                  <div class="modal-footer">
                    <a href="delete_outlook/${outlook.id}" style="text-decoration: none;"><i class="fa-solid fa-trash-can" style="color: red; font-size: 20px;"></i></a>
                  </div>
                </div>
              </div>
            </div>
            

            <!-- update modal -->
            <div class="modal fade" id="OutlookModalUpdate${outlook.id}" tabindex="-1" aria-labelledby="LoginModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <div class="container text-center">
                       <h5 class="modal-title" id="LoginModalLabel">Update Outlook</h5>
                    </div>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                      <form action="update_outlook" method="post">
                        <input type="text" class="form-control" id="id" aria-describedby="emailHelp" name="id" value="${outlook.id}" hidden>
                          <div class="mb-3">
                              <label for="title" class="form-label">Title</label>
                              <input type="text" class="form-control" id="title" aria-describedby="emailHelp" name="title" value="${outlook.title}">
                          </div>
                          <div class="mb-3">
                              <label for="body" class="form-label">Content</label>
                              <textarea class="form-control" id="body" rows="3" name="body">${outlook.body}</textarea>
                          </div>
                          <div class="container text-center">
                              <button type="submit" class="btn btn-primary">Update Outlook</button>
                          </div>
                        </form>
                  </div>
                  <div class="modal-footer">
                  </div>
                </div>
              </div>
            </div>

            <!-- comment modal -->
            <div class="modal fade" id="OutlookCommentModal${outlook.id}" tabindex="-1" aria-labelledby="LoginModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <div class="container ">
                       <!-- <h5 class="modal-title" id="LoginModalLabel">Outlook Comments</h5> -->
                       <form class="row g-2" id="comment-form${outlook.id}">
    
                          <div class="col-10">
                              <input  type="text" class="form-control" id="comment${outlook.id}" aria-describedby="emailHelp" name="comment" placeholder="Add your comment here" />
                          </div>
    
                          <div class="col-auto">
                            <button type="submit" class="btn btn-secondary" ><i class="fas fa-paper-plane" style="font-size: 20px; color: aliceblue;"></i></button>
                          </div>
                        
                        </form>
                    </div>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                     

                  </div>
                  <div class="modal-body">
                      <div class="row">
                        <div class="col"></div>
                        <div class="col-12" id="commentModalBody${outlook.id}">
                          <c:forEach var="comment" items="${outlook.comments_formatted}">
                            <div class="card border-info shadow-lg mb-3 rounded" >
                              <div class="card-header bg-transparent border-success">
                                <c:if test="${comment.user.id==loggedInUser.id}">
                                  <span style="margin-left: 375px; margin-right: 10px;"><button data-bs-toggle="modal" data-bs-target="#OutlookModalUpdate${outlook.id}" style="padding: 0; background: none; border: none;"><i class="fa-solid fa-pen-nib" style="color: blue; font-size: 20px;"></i></button></span>
                                  <span><button data-bs-toggle="modal" data-bs-target="#OutlookModalDelete${outlook.id}" style="padding: 0; background: none; border: none;"><i class="fa-solid fa-trash-can" style="color: red; font-size: 20px;"></i></button></span>
                                  <br>
                                </c:if>
                                <span><a href="" style="text-decoration: none;"><i class="fa-solid fa-user" style="font-size: 30px; color: black;"></i>&nbsp;&nbsp;</a><b>${comment.user.name}</b></span>
                                <br>
                                <span style="color: gray;">${comment.updated_at_formatted}</span>
                              </div>
                              <div class="card-body text-dark text-center">
                                <p class="card-text">${comment.content}</p>
                              </div>
                              <!-- <div class="card-footer bg-transparent border-success">
                                
                              </div> -->
                            </div>

                         </c:forEach>


                          <!-- deleted later -->
                          
                          <!-- deleted later -->
                        </div>
                        <div class="col"></div>
                      </div>
                  </div>
                  <div class="modal-footer">
                    
                  </div>
                </div>
              </div>
            </div>

          </c:forEach>

          <!-- deleted later -->
         

          <!-- deleted later -->
          
        </div>
        <div class="col"></div>
      </div>
    </div>

    <c:forEach var="outlook" items="${outlooks}">
      <script>
              $(document).ready(function(){
        
                $("#loveajaxresponse${outlook.id}").on("click","#loveajax${outlook.id}",function(event){
                  event.preventDefault();
                  $.ajax({
                    type:"GET",
                    url:"love/${outlook.id}",
                    success:function(response){
                      let result=response.split(" ");
                      if(result[0]=="true"){
                        $("#loveajaxresponse${outlook.id}").empty();
                        $("#loveajaxresponse${outlook.id}").append("<span><a id='loveajax${outlook.id}' href='#' style='text-decoration: none;'><i class='fa-solid fa-heart' style='font-size: 20px;color: red;'></i></a>&nbsp;&nbsp;"+result[1]+" Loves</span>")
                      }
                      else if(result[0]=="false"){
                        $("#loveajaxresponse${outlook.id}").empty();
                        $("#loveajaxresponse${outlook.id}").append("<span><a id='loveajax${outlook.id}' href='#' style='text-decoration: none;'><i class='fa-regular fa-heart' style='font-size: 20px;color: #000;'></i></a>&nbsp;&nbsp;"+result[1]+" Loves</span>")
                      }
                    },
                    error: function (response) {
                      alert('An error occured')
                    }
                  });
                });




              });


          $(document).ready(function () {
          $(document).on('submit', '#comment-form${outlook.id}', function (e) {
            e.preventDefault();

            $.ajax({
              type: 'POST',
              url: 'create_comment/${outlook.id}',
              data: {
                comment: $('#comment${outlook.id}').val(),
              },
              success: function (data) {
                //alert(data)
              },
              error: function (data) {
                // there was an error.
                alert("An error occured!");  
              }
            });
            document.getElementById('comment${outlook.id}').value = '';
            
          });


          setInterval(function(){
            $.ajax({
              type:'GET',
              url:'get_comments/${outlook.id}',
              success:function(comment){

                  $("#commentModalBody${outlook.id}").empty();
                  let loggedInUser_id=`${loggedInUser.id}`;
                  $("#commentsCount${outlook.id}").empty();
                  $("#commentsCount${outlook.id}").append(`<button data-bs-toggle="modal" data-bs-target="#OutlookCommentModal${outlook.id}" style="padding: 0; background: none; border: none;"><i class="fa-regular fa-comment" style="font-size: 20px;color: #000;"></i></button>&nbsp;&nbsp;\${comment.length} Comments`);
                  for(let idx in comment){
                      //delete and update button code
                      let temp1=`<span style="margin-left: 415px; margin-right: 10px;"><a href="delete_comment/\${comment[0].id}"><i class="fa-solid fa-trash-can" style="color: red; font-size: 20px;"></i></a></span><br>`;
                      // empty string to insert in js ternary operator else condition
                      let temp2="";
                      // final html code as string to be appended in card
                      let temp=`<div class="card border-info shadow-lg mb-3 rounded" >
                                    <div class="card-header bg-transparent border-success">
                                      \${comment[idx].user_id==loggedInUser_id?temp1:temp2}
                                        
                                      <span><a href="" style="text-decoration: none;"><i class="fa-solid fa-user" style="font-size: 30px; color: black;"></i>&nbsp;&nbsp;</a><b>\${comment[idx].user_name}</b></span>
                                      <br>
                                      <span style="color: gray;">\${comment[idx].updated_at_formatted}</span>
                                    </div>
                                    <div class="card-body text-dark text-center">
                                      <p class="card-text">\${comment[idx].content}</p>
                                    </div>
                                  </div>`;

                                  

                                  $("#commentModalBody${outlook.id}").append(temp); 
                  }
              },
              error: function (response) {
                // there was an error.
                alert("An error occured!");  
              }
            });
          },1000);
        });
      </script>
    </c:forEach>
</body>
</html>
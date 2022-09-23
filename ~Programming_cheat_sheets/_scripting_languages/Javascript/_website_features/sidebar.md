# Sidebar

```html
<!DOCTYPE html>
<html>
    <head>
        <title>SideBar Menu</title>
        <style type="text/css">
            body{
                margin:0px;
                padding:0px;
                font-family:"Helvetica Neue", Helvetica, Arial;
            }

            #sidebar{
                background:#151718;
                width:200px;
                height:100%;
                display:block;
                position:absolute;
                left:-200px;
                top:0px;
                transition:left 0.3s linear;
            }

            #sidebar.visible{
                left:0px;
                transition:left 0.3s linear;
            }

            ul{
                margin:0px;
                padding:0px;
            }

            ul li{
                list-style:none;
            }

            ul li a{
                background:#1C1E1F;
                color:#ccc;
                border-bottom:1px solid #111;
                display:block;
                width:180px;
                padding:10px;
                text-decoration: none;
            }

            #sidebar-btn{
                display:inline-block;
                vertical-align: middle;
                width:20px;
                height:15px;
                cursor:pointer;
                margin:20px;
                position:absolute;
                top:0px;
                right:-60px;
            }

            #sidebar-btn span{
                height: 2px;
                background: #555;
                margin-bottom: 3px;
                display: block;
            }

            #sidebar-btn span:nth-child(2){
                /*width:75%;*/
            }

            #sidebar-btn span:nth-child(3){
                /*width:50%;*/
            }

        </style>
    </head>
    <body>
        <div id="sidebar">
            <ul>
                <li><a href="#">Link 1</a></li>
                <li><a href="#">Link 2</a></li>
                <li><a href="#">Link 3</a></li>
                <li><a href="#">Link 4</a></li>
                <li><a href="#">Link 5</a></li>
            </ul>

            <div id="sidebar-btn">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <script>
            $(document).ready(function(){
                $('#sidebar-btn').click(function(){
                    $('#sidebar').toggleClass('visible');
                });
            });
        </script>
    </body>
</html>
```

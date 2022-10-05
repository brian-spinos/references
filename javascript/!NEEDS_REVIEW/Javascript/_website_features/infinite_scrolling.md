# Infinite Scrolling

```html
<!DOCTYPE html>
<html>
    <head>
        <style>
            div#status{position:fixed; font-size:24px;}
            div#wrap{width:800px; margin:0px auto;}
            div.newData{height:1000px; background:#09F; margin:10px 0px;}
            img.pic{width:800px;}
        </style>
        <script>
            function yHandler(){
                var wrap = document.getElementById('wrap');
                var contentHeight = wrap.offsetHeight;
                var yOffset = window.pageYOffset; 
                var y = yOffset + window.innerHeight;
                if(y >= contentHeight){
                    // Ajax call to get more dynamic data goes here
                    wrap.innerHTML += '<div class="newData"></div>';
                }
                var status = document.getElementById('status');
                status.innerHTML = contentHeight+" | "+y;
            }
            window.onscroll = yHandler;
        </script>
    </head>
    <body>
        <div id="status">0 | 0</div>
        <div id="wrap">
            <img class="pic" src="https://static.photocdn.pt/images/articles/2017/04/28/iStock-516651882.jpg">
            <img class="pic" src="https://static.pexels.com/photos/279315/pexels-photo-279315.jpeg">
            <img class="pic" src="https://cdn.pixabay.com/photo/2015/02/18/11/50/mountain-landscape-640617_960_720.jpg">

        </div>
    </body>
</html>
```

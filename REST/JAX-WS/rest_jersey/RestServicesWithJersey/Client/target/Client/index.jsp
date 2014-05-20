    <html>
    <head>
        <title>RESTful Web Applications with Jersey and Spring - Sample Application Client</title>
        <link rel="stylesheet" href="http://karlagius.com/wp-content/themes/karlagius.com/style.css" />
        <style type="text/css">
            li { cursor:pointer; list-style:none; }

            .code { background:none; }
            .type { font-style: italic; }
        </style>
       </head>
    <body>
    <div class="note">
        Tested on Chrome and Firefox. Make sure that both the server and client are on the same domain;
        this example uses XmlHttpRequest, which can be blocked across domains by browsers.<p/>
        More information about this sample can be found <a href="http://karlagius.com/2010/12/05/restful-web-applications-with-jersey-and-spring/">here</a>.
    </div>
    <h2>People</h2>
    <ul id="people"><li>loading ... </li></ul>
    
    <div id="detail">
        <h3>Details</h3>
        <div>Name: <span class="name"></span></div>
        <div>Surname: <span class="surname"></span></div>
        <div>Code: <span class="code"></span></div>

        <h4>Relations</h4>
        <ul class="relationships"></ul>
        <button onclick="deletePerson()">Delete</button>
    </div>

        <script type="text/javascript" src="jquery-1.4.4.min.js"></script>
        <script type="text/javascript">
            $(initialize);

            function request(url, method, callback) {
                $.ajax({ dataType: "json", type: method, url: url, success: callback });
            }

            function initialize() {
                $("#detail").hide();
                loadPeople();
            }

            function loadPeople() {
                request("http://localhost:8080/Server/people/", "GET", onPeopleLoaded);
            }

            function deletePerson() {
                var url = "http://localhost:8080/Server/people/" + $("#detail .code").text();
                request(url, "DELETE", onDeletion);
            }

            function onDeletion() {
                $("#detail").hide();
                loadPeople();
                alert("Person deleted");
            }

            function onPeopleLoaded(data) { renderPeople(data, $("#people")); }

            function renderPeople(data, container) {

               $("*", container).remove();

               for (var index in data.persons) {

                    var person = data.persons[index];
                    container.append("<li id='" + person.code + "'><span class='name'>" + person.name + "</span> <span class='surname'>" + person.surname + "</span></li>");
               }

                $("li", container).click(fetchDetail);
            }

            function fetchDetail() {
                var url = "http://localhost:8080/Server/people/" + $(this).attr("id");
                request(url, "GET", showDetail)
            }

            function showDetail(data) {
                var container = $("#detail");

                $(".name", container).text(data.name);
                $(".surname", container).text(data.surname);
                $(".code", container).text(data.code);

                $("li", container).remove();

                var relations = $("ul", container);

                if (data.relationships instanceof Array) {
                    for (var index in data.relationships) {
                        var rel = data.relationships[index];
                        relations.append("<li id='" + rel.to.code + "'><span class='name'>" + rel.to.name + "</span> <span class='surname'>" + rel.to.surname + "</span><span class='relationship'>(" + rel.type + ")</span></li>");
                    }
                } else {
                    var rel = data.relationships;
                    relations.append("<li id='" + rel.to.code + "'><span class='name'>" + rel.to.name + "</span> <span class='surname'>" + rel.to.surname + "</span><span class='relationship'>(" + rel.type + ")</span></li>");
                }

                $("li", container).click(fetchDetail);
                container.show();
            }
        </script>
    </body>
    </html>

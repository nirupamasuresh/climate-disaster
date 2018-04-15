function clearDiv() {
    document.getElementById("container").innerHTML = "";
}

var map = new Datamap({
    element: document.getElementById('container'),
    scope: 'usa',
    geographyConfig: {
        highlightBorderColor: '#bada55',
        popupTemplate: function(geography, data) {
            return '<div class="hoverinfo" style="color: black">' + geography.properties.name
        },
        highlightBorderWidth: 3
    },
    setProjection: function(element) {
        projection = d3.geo.albersUsa()
            .scale(900)
            .translate([450, 300]);
        var path = d3.geo.path()
            .projection(projection);

        return {
            path: path,
            projection: projection
        };
    },
});

function getAndPopulateData() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var type = $("#type").val();
    getRows(startDate, endDate, type);
    clearDiv();
}

function getRows(startDate, endDate, type) {
    startDate = startDate !== "" ? startDate + " 00:00:00" : "";
    endDate = endDate !== "" ? endDate + " 00:00:00" : "";
    $.ajax({
        type: 'GET',
        url: "http://"+ location.host + "/api/disaster",
        data: {
            "startDate": startDate,
            "endDate": endDate,
            "type": type
        },
        success: function(resp) {
            var map = new Datamap({
                scope: 'usa',
                element: document.getElementById('container'),
                setProjection: function(element) {
                    projection = d3.geo.albersUsa()
                        .scale(900)
                        .translate([450, 300]);
                    var path = d3.geo.path()
                        .projection(projection);

                    return {
                        path: path,
                        projection: projection
                    };
                },
                geographyConfig: {
                    highlightBorderColor: '#bada55',
                    popupTemplate: function(geography, data) {
                        return '<div class="hoverinfo" style="color: black">' + geography.properties.name + '</br>' +
                            'Count:' + data.count + ''
                    },
                    highlightBorderWidth: 3
                },
                fills: {
                    'VERY_HIGH': '#1B4F72',
                    'HIGH': '#2874A6',
                    'MEDIUM': '#3498DB',
                    'LOW': '#85C1E9',
                    'VERY_LOW': '#D6EAF8',
                    defaultFill: '#F8F9F9'
                },
                data: resp
            });
        },
        error: function() {
            alert('Please enter a valid date in the form dd-MM-yyyy');
        }
    });
}
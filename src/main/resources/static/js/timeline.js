function buildTimeline(labels, values, chartTitle) {
    let ctx = document.getElementById("timeline").getContext('2d');
    return new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: chartTitle,
                data: values,
                lineTension: 0,
                showLine: true,
                borderWidth: 1,
                borderColor: [
                    'rgba(54, 162, 235, 1)'
                ],
                backgroundColor: [
                    'rgba(255, 255, 255, 0)'
                ],
                pointBackgroundColor: [
                    'rgba(0, 123, 255, 1)'
                ],
                pointBorderColor: [
                    'rgba(0, 123, 255, 1)'
                ],
                pointBorderWidth: 2
            }]
        },
        options: {
            responsive: true, // Instruct chart js to respond nicely.
        }
    });
}

timelineJson = getJsonFromTable('timeline-table');
// Map json values back to label array
let timelineLabels = timelineJson.map(function (e) {
    return e.date;
});
// Map json values back to values array
let timelineValues = timelineJson.map(function (e) {
    return e.numberofcommits;
});

buildTimeline(timelineLabels, timelineValues, "Number of Commits By Date (Based on Latest Commits)");
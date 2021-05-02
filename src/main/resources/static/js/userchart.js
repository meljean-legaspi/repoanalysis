function buildGraph(labels, values, chartTitle) {
    let ctx = document.getElementById("userchart").getContext('2d');
    return new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: chartTitle,
                data: values,
                lineTension: 0,
                showLine: true,
                borderWidth: 1
            }]
        },
        options: {
            responsive: true, // Instruct chart js to respond nicely.
        }
    });
}

userStatsJson = getJsonFromTable('userstats-table')
// Map json values back to label array
let userStatsLabels = userStatsJson.map(function (e) {
    return e.username;
});
// Map json values back to values array
let userStatsValues = userStatsJson.map(function (e) {
    return e.numberofcommits;
});
buildGraph(userStatsLabels, userStatsValues, "Number of Commits By User (Based on Latest Commits)");
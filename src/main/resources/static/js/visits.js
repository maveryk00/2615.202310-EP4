window.onload = (event) => {

    getVisits()


    function getVisits() {
        fetch('/api/visits')
            .then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(response => {
                console.log(response)
                fillTableVisits(response)
            })
    }

    function fillTableVisits(visits) {
        const tbody = document.querySelector('tbody')

        visits.forEach(visit => {
            const tr = document.createElement('tr')

            Object.entries(visit).forEach(([key, value]) => {
                const td = document.createElement('td')
                td.innerText = value

                tr.appendChild(td)
            })

            tbody.appendChild(tr)
        })
    }

}
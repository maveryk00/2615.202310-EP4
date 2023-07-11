window.onload = (event) => {

    const btnLogin = document.querySelector('#btn-login')
    btnLogin.addEventListener('click', (event) => {
        let formData = new FormData()
        formData.append('username', document.querySelector('#username').value)
        formData.append('password', document.querySelector('#password').value)

        fetch('/api/login', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json().then(data => ({status: response.status, data})))
        .catch(error => console.error('Error:', error))
        .then(response => {
            console.log(response)
            M.toast({html: response.data.message})

            if (response.status == 200)
                setTimeout(() => {
                window.location.replace('/visits')
            }, 1000)
        })
    })

}
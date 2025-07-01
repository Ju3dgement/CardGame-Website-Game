//const updateConsoleOutput = () => {
//    fetch('/console-output')
//        .then(response => response.text())
//        .then(data => {
//            document.getElementById('output-console-extra').innerText = data;
//        })
////        .catch(err => console.error('Error fetching console output:', err));
//        .catch(() => {});
//};

const updateConsoleOutput = () => {
    fetch('/console-output')
        .then(response => response.text())
        .then(data => {
            const consoleElement = document.getElementById('output-console-extra');
            consoleElement.innerText = data;

            consoleElement.scrollTop = consoleElement.scrollHeight;
        })
        .catch(() => {});
};

const submitPlayerInput = () => {
    const input = document.getElementById('player-input').value;

    fetch('/submit-input', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `input=${encodeURIComponent(input)}`
    })
        .then(() => {
            document.getElementById('player-input').value = '';
            return fetch('/console-output');
        })
        .then(response => response.text())
        .then(data => {
            document.getElementById('output-console-extra').innerText = data; // Update console
        })
//        .catch(err => console.error('Error submitting player input:', err));
        .catch(() => {});
};

const updatePlayerHands = () => {
    for (let i = 1; i <= 4; i++) {
//        document.getElementById(`output-console-${i}`).innerText = "";
        fetch(`/player-hand/${i}`)
            .then(response => response.text())
            .then(data => {
                document.getElementById(`output-console-${i}`).innerText = data;
            })
//            .catch(err => console.error(`Error fetching player ${i}'s hand:`, err));
              .catch(() => {});
    }
};

setInterval(() => {
    updateConsoleOutput();
    updatePlayerHands();
}, 100);

document.getElementById('submit-console')?.addEventListener('click', () => {
    fetch('/clear-console', { method: 'POST' })
        .then(() => {
            const outputElement = document.getElementById('output-console-extra');
            if (outputElement) {
                outputElement.innerText = ''; // Clear the frontend console
            }
        })
//        .catch(err => console.error('Error clearing console:', err));
        .catch(() => {});
});

document.getElementById('submit-player-input').addEventListener('click', submitPlayerInput);
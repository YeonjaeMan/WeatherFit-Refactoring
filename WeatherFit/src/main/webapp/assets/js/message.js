function sendMessage() {
	const messageInput = document.getElementById('message-input');
	const message = messageInput.value;

	if (message.trim() !== '') {
		const chatContainer = document.getElementById('chat-container');
		const messageElement = document.createElement('div');
		messageElement.classList.add('message');
		messageElement.textContent = message;
		chatContainer.appendChild(messageElement);
		messageInput.value = ''; // 입력란을 비웁니다.
	}
}
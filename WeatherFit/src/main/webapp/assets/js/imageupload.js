$(document).on("change", "#imageUpload", function(event) {
	let file = event.target.files[0];
	let reader = new FileReader();

	reader.onload = function(e) {
		$("#imagePreview").attr("src", e.target.result).show();
	}

	reader.readAsDataURL(file);
});

$(document).on("change", "#update-imageUpload", function(event) {
	let file = event.target.files[0];
	let reader = new FileReader();

	reader.onload = function(e) {
		$("#update-imagePreview").attr("src", e.target.result).show();
	}

	reader.readAsDataURL(file);
});

/*document.getElementById('imageUploadForm').addEventListener('submit', function(event) {
	event.preventDefault();
	var formData = new FormData(this);
	var loadingAnimation = document.getElementById('loadingAnimation');
	var imageContainer = document.getElementById('imageContainer');

	// Show loading animation
	loadingAnimation.classList.remove('hidden');
	imageContainer.classList.add('hidden');

	// Simulate image upload delay (replace with actual AJAX image upload)
	setTimeout(function() {
		// Hide loading animation and show uploaded image
		loadingAnimation.classList.add('hidden');
		imageContainer.classList.remove('hidden');
		var uploadedImage = document.getElementById('uploadedImage');
		uploadedImage.src = 'uploaded_image.jpg'; // Replace with actual image URL
	}, 2000); // Replace with actual AJAX image upload process
});*/
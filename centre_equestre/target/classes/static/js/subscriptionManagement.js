window.onload = function () {
  var urlParams = new URLSearchParams(window.location.search);
  var errorMessage = urlParams.get("error");
  if (errorMessage) {
    alert(errorMessage);
  }
};

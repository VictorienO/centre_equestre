window.onload = function () {
  setInterval(startAnimation, 15000); // Lance l'animation toutes les 15 secondes
};

function startAnimation() {
  var spirit = document.getElementById("spirit");
  spirit.classList.remove("spirit-animation");
  void spirit.offsetWidth; // Réinitialise l'animation
  spirit.classList.add("spirit-animation");
}

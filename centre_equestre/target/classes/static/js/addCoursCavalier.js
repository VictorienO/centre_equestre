function submitForm(event) {
  event.preventDefault(); // Empêche l'envoi du formulaire par défaut

  // Récupérer les valeurs sélectionnées
  var selectedCoursId = document.getElementById("id_cours").value;
  var cavalierId = document.getElementById("id_cav").value;

  // Construire l'URL avec les valeurs sélectionnées
  var url = "/cavaliers/" + cavalierId + "/edit/" + selectedCoursId;

  // Rediriger vers l'URL construite
  window.location.href = url;
}

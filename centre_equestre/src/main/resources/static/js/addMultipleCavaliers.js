document.getElementById("importCSVButton").onclick = function () {
  document.getElementById("fileInput").click();
};

document.getElementById("fileInput").onchange = function () {
  var file = this.files[0];
  var reader = new FileReader();
  reader.onload = function (event) {
    var contenu = event.target.result;
    var lignes = contenu.split("\n"); // Sépare le contenu en lignes
    for (var i = 0; i < lignes.length; i++) {
      var ligne = lignes[i].trim(); // Supprime les espaces en début et fin de ligne
      if (ligne !== "") {
        // Ignore les lignes vides
        var colonnes = ligne.split(","); // Sépare la ligne en colonnes
        var nom = colonnes[0];
        var prenom = colonnes[1];
        var email = colonnes[2];
        var dateNaissance = colonnes[3];
        var niveau = colonnes[4];
      }
    }
  };

  reader.readAsText(file);
  document.getElementById("fileNameDisplay").textContent = file.name;
  document.getElementById("fileNameDisplay").style.display = "block";
  document.getElementById("validateCSVButton").style.display = "inline-block";
};

document.getElementById("validateCSVButton").onclick = function () {
  var file = document.getElementById("fileInput").files[0];
  var formData = new FormData();
  formData.append("file", file);

  fetch("/cavaliers/addMultipleCavaliers", {
    method: "POST",
    body: formData,
  })
    .then((response) => {
      if (response.ok) {
        alert("Cavaliers ajoutés avec succès !");
        document.querySelector(".retour").classList.add("translateright");
        window.location.reload();
      } else {
        console.error(
          "Une erreur s'est produite lors de l'envoi du fichier CSV."
        );
      }
    })
    .catch((error) => {
      console.error("Une erreur s'est produite:", error);
    });
};

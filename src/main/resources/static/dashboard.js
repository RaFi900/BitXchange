document.addEventListener("DOMContentLoaded", () => {
    const toggleBtn = document.getElementById("toggleAsset");
    const assetInput = document.getElementById("assetValue");

    toggleBtn.addEventListener("click", () => {
        const isHidden = assetInput.type === "password";
        assetInput.type = isHidden ? "text" : "password";
        toggleBtn.classList.toggle("fa-eye");
        toggleBtn.classList.toggle("fa-eye-slash");
    });
});

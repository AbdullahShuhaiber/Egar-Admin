package com.example.egar_admin.Model;

public class Service {
    private String serviceId;
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private int serviceRating;
    private String imageUrl;

    public Service() {
        // Required empty constructor for Firebase
    }

    public Service(String serviceId, String serviceName, String serviceDescription, double servicePrice, int serviceRating, String imageUrl) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.serviceRating = serviceRating;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(int serviceRating) {
        this.serviceRating = serviceRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Store image in Firebase
//    public void uploadImageToFirebaseStorage(Bitmap bitmap, String imageName, final OnSuccessListener<Uri> onSuccessListener, final OnFailureListener onFailureListener) {
//        // Get Firebase storage reference
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();
//
//        // Create reference to image file in Firebase storage
//        StorageReference imageRef = storageRef.child("services/" + imageName + ".jpg");
//
//        // Convert image bitmap to byte array
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageData = baos.toByteArray();
//
//        // Upload image to Firebase storage
//        UploadTask uploadTask = imageRef.putBytes(imageData);
//
//        // Add listeners for success and failure
//        uploadTask.addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
//    }
}


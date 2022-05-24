package com.example.demo.DTO;

public class NewAddressInfoDTO {

    String street;
    String city;
    String postalCode;
    String birthPlace;
    String country;

   public NewAddressInfoDTO(String street, String city, String postalCode, String birthPlace, String country) {
       this.street = street;
       this.city = city;
       this.postalCode = postalCode;
       this.birthPlace = birthPlace;
       this.country = country;
   }

   public String getStreet() {
       return street;
   }

   public void setStreet(String street) {
       this.street = street;
   }

   public String getCity() {
       return city;
   }

   public void setCity(String city) {
       this.city = city;
   }

   public String getPostalCode() {
       return postalCode;
   }

   public void setPostalCode(String postalCode) {
       this.postalCode = postalCode;
   }

   public String getBirthPlace() {
       return birthPlace;
   }

   public void setBirthPlace(String birthPlace) {
       this.birthPlace = birthPlace;
   }

   @Override
   public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((birthPlace == null) ? 0 : birthPlace.hashCode());
       result = prime * result + ((city == null) ? 0 : city.hashCode());
       result = prime * result + ((country == null) ? 0 : country.hashCode());
       result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
       result = prime * result + ((street == null) ? 0 : street.hashCode());
       return result;
   }

   @Override
   public boolean equals(Object obj) {
       if (this == obj)
           return true;
       if (obj == null)
           return false;
       if (getClass() != obj.getClass())
           return false;
       NewAddressInfoDTO other = (NewAddressInfoDTO) obj;
       if (birthPlace == null) {
           if (other.birthPlace != null)
               return false;
       } else if (!birthPlace.equals(other.birthPlace))
           return false;
       if (city == null) {
           if (other.city != null)
               return false;
       } else if (!city.equals(other.city))
           return false;
       if (country == null) {
           if (other.country != null)
               return false;
       } else if (!country.equals(other.country))
           return false;
       if (postalCode == null) {
           if (other.postalCode != null)
               return false;
       } else if (!postalCode.equals(other.postalCode))
           return false;
       if (street == null) {
           if (other.street != null)
               return false;
       } else if (!street.equals(other.street))
           return false;
       return true;
   }

   public String getCountry() {
       return country;
   }

   public void setCountry(String country) {
       this.country = country;
   }
}
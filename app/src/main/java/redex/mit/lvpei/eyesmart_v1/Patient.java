package redex.mit.lvpei.eyesmart_v1;

import java.util.List;

/**
 * Created by arjunpola on 15/07/14.
 */
public class Patient {

        static final String First_Name = "FIRSTNAME";
        static final String Last_Name = "LASTNAME";
        static final String Gender = "GENDER";
        static final String Age = "AGE";
        static final String Date_Of_Birth = "DOB";
        static final String Email_ID = "EMAILID";
        static final String PhoneNumber = "PHONENUMBER";
        static final String Country="COUNTRY";
        static final String State="STATE";
        static final String District="DISTRICT";

        static final String Location="LOCATION";
        static final String PinCode="PINCODE";

        static final String Address="ADDRESS";

        static final String ReferalName="REFERALNAME";

        static final String RefAddress="REFERALADDRESS";

        static final String VisionTechnician="VISIONTECHNICIAN";
        static  final  String CheckinTime="CHECKINTIME";

   private String fName,lName,pgender,pAge,dob,phone,pcountry,pstate,dist,plocation,ppin,paddress,referal,referalAdd,visionTech,checkintime,email;
   private List<Patient> patients;

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getAge() {
        return pAge;
    }

    public String getGender() {
        return pgender;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return pcountry;
    }

    public String getDist() {
        return dist;
    }

    public String getLocation() {
        return plocation;
    }

    public String getPin() {
        return ppin;
    }

    public String getAddress() {
        return paddress;
    }

    public String getReferal() {
        return referal;
    }

    public String getReferalAdd() {
        return referalAdd;
    }

    public static String getCheckinTime() {
        return CheckinTime;
    }

    public  void setCheckinTime(String checkinTime) {
        this.checkintime = checkinTime;
    }

    public static String getEmail_ID() {
        return Email_ID;
    }

    public String getVisionTech() {
        return visionTech;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setAge(String pAge) {
        this.pAge = pAge;
    }

    public void setGender(String pgender) {
        this.pgender = pgender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCountry(String pcountry) {
        this.pcountry = pcountry;
    }

    public void setState(String pstate) {
        this.pstate = pstate;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public void setLocation(String plocation) {
        this.plocation = plocation;
    }

    public void setPin(String ppin) {
        this.ppin = ppin;
    }

    public void setAddress(String paddress) {
        this.paddress = paddress;
    }

    public void setReferal(String referal) {
        this.referal = referal;
    }

    public void setRefAddress(String refAddress) {
        this.referalAdd = refAddress;
    }

    public void setVisionTech(String visionTech) {
        this.visionTech = visionTech;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }



}

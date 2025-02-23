//package _gi.projet_dev.patient_management.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//
//public class JwtService {
//
//    // Method to validate the JWT token
//    public static boolean isValid(String jwtToken) {
//        // Load the secret key securely
//        String secret = "aesr2rdlddkdkkeordojrodorjfhhgrorh2j3o3o422pcjcjjcid9jdkhjdjkkd1"; // Replace with environment variable
//        Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//
//        try {
//            // Parse and validate the JWT
//            Jws<Claims> jwsClaims = Jwts.parserBuilder()
//                    .setSigningKey(secretKey) // Use your secret or public key
//                    .build()
//                    .parseClaimsJws(jwtToken);
//
//            // Log JWT components (for debugging purposes, remove in production)
//            System.out.println("Header: " + jwsClaims.getHeader());
//            System.out.println("Body: " + jwsClaims.getBody());
//            System.out.println("Signature: " + jwsClaims.getSignature());
//
//            return true; // Token is valid
//        } catch (JwtException e) {
//            // Handle invalid token
//            System.err.println("Invalid JWT: " + e.getMessage());
//            return false;
//        }
//    }
//}

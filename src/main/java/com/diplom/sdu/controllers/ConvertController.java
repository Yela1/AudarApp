package com.diplom.sdu.controllers;

import com.diplom.sdu.models.Text;
import com.diplom.sdu.repository.TextRepository;
import com.diplom.sdu.repository.UserRepository;
import com.diplom.sdu.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Locale;

import static java.lang.Character.toUpperCase;


@RestController
@RequestMapping(value = "/convert")
public class ConvertController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TextRepository textRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/cyr")
    public ResponseEntity<Text> cyrToLat(@RequestBody Text text){
        String str = cyr2lat(text.getText());
        text.setAnother_string(str);
        text.setText(str.replaceAll("[∑©‘øœ]",""));
        return ResponseEntity.status(HttpStatus.OK).body(text);
    }


    @PostMapping(value = "/lat")
    public ResponseEntity<Text> latToCyr(@RequestBody Text text){


        text.setText(lat2cyr(text.getText()));
        return ResponseEntity.status(HttpStatus.OK).body(text);
    }

    @PostMapping(value = "/favorite")
    public ResponseEntity<?> favorite(@RequestBody Text text, HttpServletRequest request){

        String jwt = parseJwt(request);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            text.setUser(username);
            return ResponseEntity.status(HttpStatus.CREATED).body(textRepository.save(text));

        }
        return null;

    }

    @PostMapping(value = "/getFavorite")
    public ResponseEntity<List<Text>> getFavorite(HttpServletRequest request){

        String jwt = parseJwt(request);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            return ResponseEntity.status(HttpStatus.OK).body(textRepository.findAllByUser(username));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    public static String lat2cyr(String s){

        Locale.setDefault(new Locale("tr"));
        StringBuilder sb = new StringBuilder(s.length());
        int i = 0;
        while(i < s.length()){

            Locale.setDefault(new Locale("tr"));
            char ch = s.charAt(i);
            boolean lc = Character.isLowerCase(ch);
            String letter = Character.toString(ch);
            letter = letter.toUpperCase();
            ch = letter.charAt(0);

            if(i+1<s.length() && (ch == 'I' && (toUpperCase(s.charAt(i+1)) == 'Ý'  || toUpperCase(s.charAt(i+1)) == 'A' || s.charAt(i+1) == 'œ'))){
                i++;
                ch = toUpperCase(s.charAt(i));
                switch (ch){
                    case 'Ý': sb.append(ch('Ю', lc));break;
                    case 'A': sb.append(ch('Я', lc)); break;
                    case 'Œ': sb.append(ch('Й', lc)); break;
                    default: throw new IllegalArgumentException("Illegal transliterated symbol '"+ch+"' at position "+i);
                }
            }
            else if(i+1<s.length() && (ch == 'C' && (toUpperCase(s.charAt(i+1)) == 'H'))){

                i++;
                ch = toUpperCase(s.charAt(i));
                switch (ch){
                    case 'H': sb.append(ch('Ч', lc));break;
                    default: throw new IllegalArgumentException("Illegal transliterated symbol '"+ch+"' at position "+i);
                }
            }else if(i+1<s.length() && (ch == 'S' && (toUpperCase(s.charAt(i+1)) == 'H'))){
                i++;
                ch = toUpperCase(s.charAt(i));
                switch (ch){
                    case 'H':
                        i++;

                        ch=toUpperCase(s.charAt(i));
                        if( ch =='Ø'){
                            sb.append(ch('Щ',lc));
                        }else{
                            sb.append(ch('Ш',lc));
                        }break;
                    default: throw new IllegalArgumentException("Illegal transliterated symbol '"+ch+"' at position "+i);
                }
            }else if(i+1<s.length() && (ch == 'T' && (toUpperCase(s.charAt(i+1)) == 'S'))){

                i++;
                ch = toUpperCase(s.charAt(i));
                switch (ch){
                    case 'S': sb.append(ch('Ц', lc));break;
                    default: throw new IllegalArgumentException("Illegal transliterated symbol '"+ch+"' at position "+i);
                }
            } else if(i+1<s.length() && (ch == 'Y' && (toUpperCase(s.charAt(i+1)) == 'O'))){
                i++;
                ch = toUpperCase(s.charAt(i));
                switch (ch){
                    case 'O': sb.append(ch('Ё', lc));break;
                    default: throw new IllegalArgumentException("Illegal transliterated symbol '"+ch+"' at position "+i);
                }
            }
            else if(i+1<s.length() && (ch == 'H' && (toUpperCase(s.charAt(i+1)) == '™'))){
                i++;
                ch = toUpperCase(s.charAt(i));
                switch (ch){
                    case '™': sb.append(ch('Һ', lc));break;
                    default: throw new IllegalArgumentException("Illegal transliterated symbol '"+ch+"' at position "+i);
                }
            }
            else if(i+1<s.length() && (ch == 'E' && (toUpperCase(s.charAt(i+1)) == '∑'))){
                i++;
                ch = toUpperCase(s.charAt(i));
                switch (ch){
                    case '∑': sb.append(ch('Э', lc));break;

                    default: throw new IllegalArgumentException("Illegal transliterated symbol '"+ch+"' at position "+i);
                }
            }

            else{
                switch (ch){
                    case 'A': sb.append(ch('А', lc)); break;
                    case 'Á': sb.append(ch('Ә', lc)); break;
                    case 'B': sb.append(ch('Б', lc)); break;
                    case 'V': sb.append(ch('В', lc)); break;
                    case 'G': sb.append(ch('Г', lc)); break;
                    case 'Ğ': sb.append(ch('Ғ', lc)); break;
                    case 'D': sb.append(ch('Д', lc)); break;
                    case 'E': sb.append(ch('Е', lc)); break;
                    case 'J': sb.append(ch('Ж', lc)); break;
                    case 'Z': sb.append(ch('З', lc)); break;
                    case 'I': sb.append(ch('И', lc)); break;
                    case 'K': sb.append(ch('К', lc)); break;
                    case 'Q': sb.append(ch('Қ', lc)); break;
                    case 'L': sb.append(ch('Л', lc)); break;
                    case 'M': sb.append(ch('М', lc)); break;
                    case 'N': sb.append(ch('Н', lc)); break;
                    case 'Ń': sb.append(ch('Ң', lc)); break;
                    case 'O': sb.append(ch('О', lc)); break;
                    case 'Ó': sb.append(ch('Ө', lc)); break;
                    case 'P': sb.append(ch('П', lc)); break;
                    case 'R': sb.append(ch('Р', lc)); break;
                    case 'T': sb.append(ch('Т', lc)); break;
                    case 'Ý': sb.append(ch('У', lc)); break;
                    case 'U': sb.append(ch('Ұ', lc)); break;
                    case 'Ú': sb.append(ch('Ү', lc)); break;
                    case 'F': sb.append(ch('Ф', lc)); break;
                    case 'H': sb.append(ch('Х', lc)); break;
                    case 'Y': sb.append(ch('Ы', lc)); break;
                    case 'İ': sb.append(ch('I', lc)); break;
                    case 'S': sb.append(ch('С', lc)); break;
                    case '‘': sb.append(ch('ъ', lc)); break;
                    case '©': sb.append(ch('ь', lc)); break;

                    default: sb.append(ch(ch, lc));
                }
            }

            i++;
        }
        return sb.toString();
    }

    public static String cyr2lat(char ch){
        switch (ch){
            case 'А': return "A";
            case 'Ә': return "Á";
            case 'Б': return "B";
            case 'В': return "V";
            case 'Г': return "G";
            case 'Ғ': return "Ǵ";
            case 'Д': return "D";
            case 'Е': return "E";
            case 'Ё': return "Yo";
            case 'Ж': return "J";
            case 'З': return "Z";
            case 'И': return "I";
            case 'Й': return "Iœ";
            case 'К': return "K";
            case 'Қ': return "Q";
            case 'Л': return "L";
            case 'М': return "M";
            case 'Н': return "N";
            case 'Ң': return "Ń";
            case 'О': return "O";
            case 'Ө': return "Ó";
            case 'П': return "P";
            case 'Р': return "R";
            case 'С': return "S";
            case 'Т': return "T";
            case 'У': return "Ý";
            case 'Ұ': return "U";
            case 'Ү': return "Ú";
            case 'Ф': return "F";
            case 'Х': return "H";
            case 'Һ': return "H™";
            case 'Ц': return "Ts";
            case 'Ч': return "Ch";
            case 'Ш': return "Sh";
            case 'Щ': return "Shø";
            case 'Ъ': return "‘" ;
            case 'Ы': return "Y";
            case 'I': return "İ";
            case 'Ь': return "©";
            case 'Э': return "E∑";
            case 'Ю': return "Iý";
            case 'Я': return "Ia";
            default: return String.valueOf(ch);
        }
    }


    public static String cyr2lat(String s){
        Locale.setDefault(new Locale("tr"));
        StringBuilder sb = new StringBuilder(s.length()*2);
        for(char ch: s.toCharArray()){
            char upCh = toUpperCase(ch);
            String lat = cyr2lat(upCh);
            if(ch != upCh){
                lat = lat.toLowerCase();
            }
            sb.append(lat);
        }
        return sb.toString();
    }
    private static char ch(char ch, boolean toLowerCase){

        Locale.setDefault(new Locale("tr"));

        String letter = Character.toString(ch).toLowerCase();

        return toLowerCase? letter.charAt(0): ch;
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }


}

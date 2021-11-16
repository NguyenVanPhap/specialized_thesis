package com.elearning.api.admin;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elearning.entities.Vocabulary;
//import com.elearning.entities.BaiThiThu;
//import com.elearning.entities.CauHoiBaiThiThu;
import com.elearning.entities.VocabularyContent;
import com.elearning.service.VocabularyService;
import com.elearning.service.DetailVocabularyService;

@RestController
@RequestMapping("/api/admin/vocab")
public class VocabApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    VocabularyService baitaptuvungService;
    @Autowired
    DetailVocabularyService noidungbaitaptuvungService;

    @GetMapping("/loadVocab")
    public List<String> showAllVocab() {

        List<Vocabulary> list = baitaptuvungService.findAll();

        List<String> response = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            String json = "baitaptuvungid:" + list.get(i).getVocabularyId() + "," + "anhbaituvung:"
                    + list.get(i).getImage() + "," + "tenbaituvung:" + list.get(i).getVocabularyName();

            response.add(json);
        }

        return response;

    }

    @RequestMapping(value = "/delete/{idBaiVocab}")
    public String deleteById(@PathVariable("idBaiVocab") int id) {
        baitaptuvungService.delete(id);
        return "success";
    }

    @PostMapping(value = "/save", consumes = "multipart/form-data")
    @ResponseBody
    public List<String> addBaiThiThu(@RequestParam("file_excel") MultipartFile file_excel,
                                     @RequestParam("file_image") MultipartFile file_image, @RequestParam("name") String name,
                                     @RequestParam("file_image_question") MultipartFile[] file_image_question,
                                     @RequestParam("file_listening") MultipartFile[] file_listening) {

        List<String> response = new ArrayList<String>();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        Vocabulary bttuvung = new Vocabulary();
        baitaptuvungService.save(bttuvung);

        // System.out.println("id="+baithithu.getBaithithuid());
        try {
            // save file upload to local folder
            Path pathExcel = Paths.get(rootDirectory + "/resources/file/excel/" + "vocab."
                    + bttuvung.getVocabularyId() + "." + file_excel.getOriginalFilename());
            file_excel.transferTo(new File(pathExcel.toString()));

            Path pathImage = Paths.get(rootDirectory + "/resources/file/images/vocab/" + ""
                    + bttuvung.getVocabularyId() + "." + file_image.getOriginalFilename());
            file_image.transferTo(new File(pathImage.toString()));

            for (MultipartFile single_image : file_image_question) {
                Path pathImageQuestion = Paths.get(rootDirectory + "/resources/file/images/vocab/" + ""
                        + bttuvung.getVocabularyId() + "." + single_image.getOriginalFilename());
                single_image.transferTo(new File(pathImageQuestion.toString()));
            }

            for (MultipartFile single_listening : file_listening) {
                Path pathListening = Paths.get(rootDirectory + "/resources/file/audio/vocab/" + ""
                        + bttuvung.getVocabularyId() + "." + single_listening.getOriginalFilename());
                single_listening.transferTo(new File(pathListening.toString()));
            }

            bttuvung.setVocabularyName(name);
            bttuvung.setImage(bttuvung.getVocabularyId() + "." + file_image.getOriginalFilename());
            baitaptuvungService.save(bttuvung);

            // save data from file excel

            VocabApi btt = new VocabApi();
            List<VocabularyContent> listCauHoiFull = btt.getListFromExcel(pathExcel.toString(), bttuvung);

            for (int i = 0; i < listCauHoiFull.size(); i++) {
                noidungbaitaptuvungService.save(listCauHoiFull.get(i));
            }

        } catch (Exception e) {
            response.add(e.toString());
            System.out.println("ErrorReadFileExcel:" + e);

        }

        return response;
    }

    // get info Vocab ->edit Vocab
    @RequestMapping(value = "/infoVocab/{idBaiVocab}")
    public String infoVocabById(@PathVariable("idBaiVocab") int id) {
        Vocabulary bttuvung = baitaptuvungService.getBaiTapTuVung(id).get(0);

        return bttuvung.getVocabularyName();
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public List<String> updateBaiVocab(@RequestParam("vocabId") int id, @RequestParam("name") String name,
                                       @RequestParam("file_image") MultipartFile file_image,
                                       @RequestParam("file_image_question") MultipartFile[] file_image_question,
                                       @RequestParam("file_listening") MultipartFile[] file_listening,
                                       @RequestParam("file_excel") MultipartFile file_excel) {

        List<String> response = new ArrayList<String>();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        Vocabulary bttuvung = baitaptuvungService.getBaiTapTuVung(id).get(0);

        // System.out.println("id="+baithithu.getBaithithuid());
        try {
            // save file upload to local folder
            Path pathExcel = Paths.get(rootDirectory + "/resources/file/excel/" + "vocab."
                    + bttuvung.getVocabularyId() + "." + file_excel.getOriginalFilename());
            file_excel.transferTo(new File(pathExcel.toString()));

            Path pathImage = Paths.get(rootDirectory + "/resources/file/images/vocab/" + ""
                    + bttuvung.getVocabularyId() + "." + file_image.getOriginalFilename());
            file_image.transferTo(new File(pathImage.toString()));

            for (MultipartFile single_image : file_image_question) {
                Path pathImageQuestion = Paths.get(rootDirectory + "/resources/file/images/vocab/" + ""
                        + bttuvung.getVocabularyId() + "." + single_image.getOriginalFilename());
                single_image.transferTo(new File(pathImageQuestion.toString()));
            }

            for (MultipartFile single_listening : file_listening) {
                Path pathListening = Paths.get(rootDirectory + "/resources/file/audio/vocab/" + ""
                        + bttuvung.getVocabularyId() + "." + single_listening.getOriginalFilename());
                single_listening.transferTo(new File(pathListening.toString()));
            }

            bttuvung.setVocabularyName(name);
            bttuvung.setImage(bttuvung.getVocabularyId() + "." + file_image.getOriginalFilename());
            baitaptuvungService.save(bttuvung);

            // save data from file excel

            VocabApi btt = new VocabApi();
            List<VocabularyContent> listCauHoiFull = btt.getListFromExcel(pathExcel.toString(), bttuvung);

            for (int i = 0; i < listCauHoiFull.size(); i++) {
                noidungbaitaptuvungService.save(listCauHoiFull.get(i));
            }

        } catch (Exception e) {
            response.add(e.toString());
            System.out.println("errorUpdate:" + e);
        }

        return response;
    }

    public List<VocabularyContent> getListFromExcel(String path_file_excel, Vocabulary bttuvung) {
        List<VocabularyContent> list = new ArrayList<>();

        try {
            FileInputStream excelFile = new FileInputStream(new File(path_file_excel));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                VocabularyContent noidungbaitaptuvung = new VocabularyContent();

                XSSFRow row = worksheet.getRow(i);

                if (row.getCell(0) != null)
                    noidungbaitaptuvung.setNumber((int) row.getCell(0).getNumericCellValue());

                if (row.getCell(1) != null)
                    noidungbaitaptuvung.setContent(row.getCell(1).getStringCellValue());

                if (row.getCell(2) != null)
                    noidungbaitaptuvung.setTranscribe(row.getCell(2).getStringCellValue());

                if (row.getCell(3) != null)
                    noidungbaitaptuvung.setImage(
                            bttuvung.getVocabularyId() + "." + row.getCell(3).getStringCellValue().toString());

                if (row.getCell(4) != null)
                    noidungbaitaptuvung
                            .setAudiomp3(bttuvung.getVocabularyId() + "." + row.getCell(4).getStringCellValue());

                if (row.getCell(5) != null)
                    noidungbaitaptuvung.setMeaning(row.getCell(5).getStringCellValue());

                if (row.getCell(6) != null)
                    noidungbaitaptuvung.setSentence(row.getCell(6).getStringCellValue());

                noidungbaitaptuvung.setBaitaptuvung(bttuvung);

                list.add(noidungbaitaptuvung);
            }

        } catch (Exception e) {
            System.out.println("errorhere:" + e);
        }

        return list;

    }

}

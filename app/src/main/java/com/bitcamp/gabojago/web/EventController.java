package com.bitcamp.gabojago.web;

import com.bitcamp.gabojago.service.EventService;
import com.bitcamp.gabojago.vo.Member;
import com.bitcamp.gabojago.vo.event.Event;
import com.bitcamp.gabojago.vo.event.EventAttachedFile;
import com.bitcamp.gabojago.vo.event.EventItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/support/event/")
public class EventController {

    ServletContext sc;
    @Autowired
    EventService eventService;

    public EventController(EventService eventService, ServletContext sc) {
        System.out.println("EventController() 호출됨>-<");
        this.eventService = eventService;
        this.sc = sc;
    }

    @GetMapping("list")
    public void list(Model model) throws Exception {
        model.addAttribute("events", eventService.list());
    }

    @GetMapping("addForm")
    public void addForm() throws Exception {

    }

    @PostMapping("add")
    public String add(
            Event event,
            HttpSession session) throws Exception {
        System.out.println("eventController : " + event.toString());
        eventService.add(event);
        return "redirect:list";
    }

    @GetMapping("detail")
    public void detail(int no, Model model) throws Exception {
        model.addAttribute("event", eventService.get(no));
        model.addAttribute("eventItems", eventService.itemList(no));
    }

    @GetMapping("delete")
    public String delete(int no) throws Exception {
        eventService.delete(no);
        return "redirect:list";
    }

    @GetMapping("editForm")
    public void editForm(int no, Model model) throws Exception {
        model.addAttribute("event", eventService.get(no));
    }

    @PostMapping("update")
    public String update(
            Event event,
            HttpSession session) throws Exception {
        System.out.println("EventControllerUpdate :" + event.toString());
        eventService.update(event);
        return "redirect:list";
    }

    @GetMapping("item/addForm")
    public void itemAddForm(int no, Model model) throws Exception {
        model.addAttribute("eventNo", no);
    }

    @PostMapping("item/itemadd")
    public String itemAdd(
            EventItem eventItem,
            MultipartFile file,
            HttpSession session) throws Exception {
        System.out.println("eventCotrollerAdd_item before:" + eventItem.toString());
        EventAttachedFile eventAttachedFile = saveAttachedFile(file);
        eventItem.setEventAttachedFile(eventAttachedFile);
        System.out.println("eventCotrollerAdd_item after :" + eventItem.toString());
        eventService.itemAdd(eventItem);
        return "redirect:../detail?no=" + eventItem.getEventNo();
    }

    private EventAttachedFile saveAttachedFile(MultipartFile file)
            throws IOException, ServletException {
        EventAttachedFile eventAttachedFile = new EventAttachedFile();
        String dirPath = sc.getRealPath("/event/files");
        System.out.println("tttt : " + sc.getContextPath());
        if (file.isEmpty()) {
            return eventAttachedFile;
        }

        String fileName = UUID.randomUUID().toString();
        System.out.println("new file name : " + fileName);
        System.out.println("new file path before : " + (dirPath + "/"));
        System.out.println("new file path before : " + file.getContentType());
        file.transferTo(new File(dirPath + "/" + fileName + "." + file.getContentType().split("/")[1]));
        System.out.println("new file path after : " + (dirPath + "/"));
        eventAttachedFile.setFileName(fileName + "." + file.getContentType().split("/")[1]);
        eventAttachedFile.setFilePath(dirPath+"/");
        return eventAttachedFile;
    }
//
//    @PostMapping("item/itemadd")
//    public String itemAdd(
//            EventItem eventItem,
//            MultipartFile file,
//            HttpSession session) throws Exception {
//        System.out.println("eventCotrollerAdd_item before:" + eventItem.toString());
//        eventItem.setEventAttachedFile(saveEventAttachedFile(file));
//        System.out.println("eventCotrollerAdd_item after :" + eventItem.toString());
//        eventService.itemAdd(eventItem);
//        return "redirect:../detail?no=" + eventItem.getEventNo();
//
//    }
//
//    private EventAttachedFile saveEventAttachedFile(Part file)
//            throws IOException, ServletException {
//        EventAttachedFile eventAttachedFile = new EventAttachedFile();
//        String dirPath = sc.getRealPath("/event/files");
//
//            if (file.getSize() == 0) {
//
//            }
//
//            String filename = UUID.randomUUID().toString();
//            file.write(dirPath + "/" + filename);
//            eventAttachedFile = new EventAttachedFile(filename);
//        return attachedFile;
//    }
//
//    private EventAttachedFile saveEventAttachedFile(MultipartFile file)
//            throws IOException, ServletException {
//        EventAttachedFile attachedFile = new EventAttachedFile();
//        String dirPath = sc.getRealPath("/event/files");
//
//        for (MultipartFile part : file) {
//            if (part.isEmpty()) {
//                continue;
//            }
//
//            String filename = UUID.randomUUID().toString();
//            part.transferTo(new File(dirPath + "/" + filename));
//            EventAttachedFile.add(new EventAttachedFile(filename));
//        }
//        return attachedFile;
//    }
//


//    private List<EventAttachedFile> saveAttachedFiles(MultipartFile[] files)
//            throws IOException, ServletException {
//        List<EventAttachedFile> eventAttachedFiles = new ArrayList<>();
//        String dirPath = sc.getRealPath("/event/files");
//
//        for (MultipartFile part : files) {
//            if (part.isEmpty()) {
//                continue;
//            }
//
//            String fileName = UUID.randomUUID().toString();
//            part.transferTo(new File(dirPath + "/" + fileName));
//            eventAttachedFiles.add(new EventAttachedFile(fileName));
//        }
//        return eventAttachedFiles;
//    }
}

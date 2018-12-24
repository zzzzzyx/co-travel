package cn.edu.nju.co_travel.serviceImpl;

import cn.edu.nju.co_travel.service.SearchService;
import org.springframework.stereotype.Service;


@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public String getHelloText() {
        return "Co-travel sends best regards!";
    }
}

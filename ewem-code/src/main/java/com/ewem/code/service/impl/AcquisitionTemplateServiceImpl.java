package com.ewem.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ewem.code.domain.AcquisitionTemplate;
import com.ewem.code.mapper.AcquisitionTemplateMapper;
import com.ewem.code.service.IAcquisitionTemplateService;
import org.springframework.stereotype.Service;

@Service
public class AcquisitionTemplateServiceImpl extends ServiceImpl<AcquisitionTemplateMapper, AcquisitionTemplate> implements IAcquisitionTemplateService {
}

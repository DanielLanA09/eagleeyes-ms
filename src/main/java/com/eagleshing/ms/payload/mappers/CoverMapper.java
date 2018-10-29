package com.eagleshing.ms.payload.mappers;

import com.eagleshing.ms.payload.CoverResponse;

public interface CoverMapper {
    CoverResponse findCover (int coverId);
}

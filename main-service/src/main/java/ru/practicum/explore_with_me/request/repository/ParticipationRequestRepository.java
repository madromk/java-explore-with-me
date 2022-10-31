package ru.practicum.explore_with_me.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explore_with_me.request.model.EventRequestState;
import ru.practicum.explore_with_me.request.model.ParticipationRequest;

import java.util.List;

@Repository
public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    Long countByEvent_IdAndStatus(Long eventId, EventRequestState confirmed);

    List<ParticipationRequest> findAllByRequesterId(long userId);

    @Query("SELECT pr FROM ParticipationRequest pr WHERE pr.event.id=?1 AND pr.event.initiator.id=?2")
    List<ParticipationRequest> findAllByEvent(long eventId, long userId);
}
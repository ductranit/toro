/*
 * Copyright (c) 2018 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.ene.toro.exoplayer;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.video.VideoListener;
import im.ene.toro.media.PlaybackInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface is designed to be reused across Config change. Implementation must not hold any
 * final reference to Activity, and if it supports any kind of that, make sure to implicitly clean
 * it up. Currently, there is no clear way to reuse an instance of this.
 *
 * @author eneim
 * @since 3.4.0
 */

public interface Playable {

  void prepare();

  // Attach a new PlayerView to current Playable.
  void setPlayerView(@Nullable PlayerView playerView);

  // Return current attached SimpleExoPlayerView.
  @Nullable PlayerView getPlayerView();

  void play();

  void pause();

  // Reset all resource, so that the playback can start all over again.
  void reset();

  // Release all resource. After this, the Playable need to be prepared again to be usable.
  void release();

  @NonNull PlaybackInfo getPlaybackInfo();

  void setPlaybackInfo(@NonNull PlaybackInfo playbackInfo);

  // Should be called before prepare() so that Client could received all events from preparation.
  void addEventListener(@NonNull EventListener listener);

  void removeEventListener(EventListener listener);

  boolean isPlaying();

  void setVolume(@FloatRange(from = 0.0, to = 1.0) float volume);

  @FloatRange(from = 0.0, to = 1.0) float getVolume();

  // Combine necessary interfaces.
  interface EventListener extends Player.EventListener, VideoListener, TextOutput, MetadataOutput {

  }

  class DefaultEventListener implements EventListener {

    @Override public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override public void onLoadingChanged(boolean isLoading) {

    }

    @Override public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override public void onRepeatModeChanged(int repeatMode) {

    }

    @Override public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override public void onPlayerError(ExoPlaybackException error) {

    }

    @Override public void onPositionDiscontinuity(int reason) {

    }

    @Override public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override public void onSeekProcessed() {

    }

    @Override public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees,
        float pixelWidthHeightRatio) {

    }

    @Override public void onRenderedFirstFrame() {

    }

    @Override public void onCues(List<Cue> cues) {

    }

    @Override public void onMetadata(Metadata metadata) {

    }
  }

  // An ArrayList of EventListener.
  class EventListeners extends ArrayList<EventListener> implements EventListener {

    EventListeners() {
    }

    @Override public void onVideoSizeChanged(int width, int height, int unAppliedRotationDegrees,
        float pixelWidthHeightRatio) {
      for (EventListener eventListener : this) {
        eventListener.onVideoSizeChanged(width, height, unAppliedRotationDegrees,
            pixelWidthHeightRatio);
      }
    }

    @Override public void onRenderedFirstFrame() {
      for (EventListener eventListener : this) {
        eventListener.onRenderedFirstFrame();
      }
    }

    @Override public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
      for (EventListener eventListener : this) {
        eventListener.onTimelineChanged(timeline, manifest, reason);
      }
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
      for (EventListener eventListener : this) {
        eventListener.onTracksChanged(trackGroups, trackSelections);
      }
    }

    @Override public void onLoadingChanged(boolean isLoading) {
      for (EventListener eventListener : this) {
        eventListener.onLoadingChanged(isLoading);
      }
    }

    @Override public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
      for (EventListener eventListener : this) {
        eventListener.onPlayerStateChanged(playWhenReady, playbackState);
      }
    }

    @Override public void onRepeatModeChanged(int repeatMode) {
      for (EventListener eventListener : this) {
        eventListener.onRepeatModeChanged(repeatMode);
      }
    }

    @Override public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
      for (EventListener eventListener : this) {
        eventListener.onShuffleModeEnabledChanged(shuffleModeEnabled);
      }
    }

    @Override public void onPlayerError(ExoPlaybackException error) {
      for (EventListener eventListener : this) {
        eventListener.onPlayerError(error);
      }
    }

    @Override public void onPositionDiscontinuity(int reason) {
      for (EventListener eventListener : this) {
        eventListener.onPositionDiscontinuity(reason);
      }
    }

    @Override public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
      for (EventListener eventListener : this) {
        eventListener.onPlaybackParametersChanged(playbackParameters);
      }
    }

    @Override public void onSeekProcessed() {
      for (EventListener eventListener : this) {
        eventListener.onSeekProcessed();
      }
    }

    @Override public void onCues(List<Cue> cues) {
      for (EventListener eventListener : this) {
        eventListener.onCues(cues);
      }
    }

    @Override public void onMetadata(Metadata metadata) {
      for (EventListener eventListener : this) {
        eventListener.onMetadata(metadata);
      }
    }
  }
}